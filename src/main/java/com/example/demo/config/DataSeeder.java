package com.example.demo.config;

import com.example.demo.model.Tenant;
import com.example.demo.model.Usuario;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final TenantRepository tenantRepository;
    private final UsuarioRepository usuarioRepository;

    public DataSeeder(TenantRepository tenantRepository, UsuarioRepository usuarioRepository) {
        this.tenantRepository = tenantRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe algum tenant no banco para não duplicar toda vez que o sistema reiniciar
        if (tenantRepository.count() == 0) {
            System.out.println("🌱 Rodando o Seeder: Criando Tenant e Usuário padrão...");

            // 1. Cria e salva o Tenant
            Tenant tenantPadrao = new Tenant();
            tenantPadrao.setNome("Tenant Master");
            tenantRepository.save(tenantPadrao);

            // 2. Cria e salva o Usuário vinculado ao Tenant recém-criado
            Usuario admin = new Usuario();
            admin.setNomeCompleto("Administrador do Sistema");
            admin.setEmail("admin@admin.com");
            admin.setSenhaHash("123456"); // Senha crua por enquanto
            admin.setAdmin(true);
            admin.setTenant(tenantPadrao);
            
            usuarioRepository.save(admin);

            System.out.println("✅ Seed finalizado com sucesso!");
        } else {
            System.out.println("👍 Banco já populado. Pulando o Seed.");
        }
    }
}